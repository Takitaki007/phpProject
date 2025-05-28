import { NextResponse } from "next/server";

export async function POST(request) {
  try {
    // Get the form data sent from the client
    const { username, email, password } = await request.json();

    // Send request to the external API
    const response = await fetch(
      "http://96.9.81.187:8080/api/v1/auth/register",
      {
        method: "POST",
        accept: "*/*",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ username, email, password }), // Send the form data
      }
    );

    // Check if the external API responded correctly
    if (!response.ok) {
      throw new Error("Failed to register user");
    }

    const data = await response.json();
    return NextResponse.json(data); // Send the response from the external API to the client
  } catch (error) {
    return NextResponse.json({ error: error.message }, { status: 500 });
  }
}
